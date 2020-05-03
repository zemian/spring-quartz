define(['text!./main-app.html'], function(html) {
    return {
        template: html,
        data: function () {
            return {
                componentName: 'list-jobs',
                componentOptions: {}
            }
        },
        created: function () {
            this.changeComponent(this.componentName);
        },
        watch: {
            componentName: function (val) {
                this.changeComponent(val);
            }
        },
        methods: {
            changeComponent: function (componentName) {
                let self = this;
                let reqPath = 'components/' + componentName;
                console.log("Loading reqPath=" + reqPath);
                require([reqPath], function (compOptions) {
                    console.log("Loaded component=" + componentName);
                    self.componentOptions = compOptions;
                });
            }
        }
    };
});
