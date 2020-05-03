define(['text!./create-job.html'], function(html) {
    return {
        template: html,
        data: function () {
            return {
                baseUrl: "http://localhost:8080",
                jobName: 'MyJob-' + Math.random().toString(16).substring(2),
                jobClassName: 'zemian.springquartz.LoggerJob',
                repeatCount: 1,
                repeatInterval: 60000,
                createdJobKey: null
            }
        },
        methods: {
            createJob: function () {
                // Generate some fake jobs
                let self = this;
                let url = this.baseUrl + "/jobs";
                let sendData = {
                    jobDetail: {
                        name: self.jobName,
                        jobClassName: self.jobClassName
                    },
                    trigger: {
                        repeatCount: self.repeatCount,
                        repeatInterval: self.repeatInterval
                    }
                };
                fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(sendData)
                }).then(resp => resp.json().then(data => {
                    console.log('Job created ', data);
                    this.createdJobKey = data;
                }));
            },
            resetNewJob: function () {
                this.createdJobKey = null;
                this.jobName = 'MyJob-' + Math.random().toString(16).substring(2);
            }
        }
    };
});
