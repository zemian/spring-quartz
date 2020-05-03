requirejs.config({
    baseUrl: 'js',
    paths: {
        'text': 'https://unpkg.com/requirejs-text@2.0.15/text',
        'vue': 'https://unpkg.com/vue@2.6.11/dist/vue.min'
    }
});

require(['vue', 'components/main-app'], function (Vue, MainApp) {
    let app = new Vue({
        components: {
            'main-app': MainApp
        }
    });
    app.$mount('#app');
});