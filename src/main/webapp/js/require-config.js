/* global requirejs */

requirejs.config({
    baseUrl: '.',
    paths: {
        CosmicRouter: 'js/router/CosmicRouter',
        CosmicBaseView: 'js/views/CosmicBaseView',
        LoginView: 'js/views/LoginView',
        RegistrationView: 'js/views/RegistrationView',
        ErrorManager: 'js/classes/ErrorManager',
        UserAccountModel: 'js/models/UserAccountModel',
        RegistrationModel: 'js/models/RegistrationModel',
        Application: 'js/classes/Application'
    }
});