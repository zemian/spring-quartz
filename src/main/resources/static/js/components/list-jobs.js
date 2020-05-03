define(['text!./list-jobs.html'], function(html) {
    return {
        template: html,
        data: function () {
            return {
                baseUrl: "http://localhost:8080",
                jobs: []
            }
        },
        created: function () {
            this.fetchJobs();
        },
        methods: {
            fetchJobs: function () {
                // Get List of Jobs from backend
                let url = this.baseUrl + "/jobs";
                fetch(url).then(resp => {
                    resp.json().then(data => {
                        console.log("fetchJobs: data", data);
                        this.jobs = data.items;
                    });
                });
            },
            createTestJobs: function () {
                // Generate some fake jobs
                let url = this.baseUrl + "/jobs";
                let batchId = Math.random().toString(16).substring(2);
                for (let i = 0; i < 10; i++) {
                    let jobName = "TestJob-" + batchId + "-" + i;
                    let sendData = {
                        jobDetail: {
                            name: jobName,
                            jobClassName: "zemian.springquartz.LoggerJob"
                        },
                        trigger: {
                            repeatCount: 300,
                            repeatInterval: 360000
                        }
                    };
                    fetch(url, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(sendData)
                    }).then(resp => resp.text().then(data => {
                        console.log('createTestJobs: created' + data);
                    }));
                }

                // Now refresh the job list
                this.fetchJobs();
            }
        }
    };
});
