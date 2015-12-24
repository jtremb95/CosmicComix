/* global Backbone */

define([
    'LoginView',
    'RegistrationView',
    'UserAccountModel',
    'RegistrationModel'
], function (
        LoginView,
        RegistrationView,
        UserAccountModel,
        RegistrationModel
        ) {
    return Backbone.Router.extend({
        routes: {
            "": "home",
            "home": "home",
            "login": "login",
            "register": "registration"
        },

        home: function () {
            // Create the account model
            if (this.userAccountModel === undefined) {
                this.userAccountModel = new UserAccountModel();
            }

            // Create the registration model
            if (this.registrationModel === undefined) {
                this.registrationModel = new RegistrationModel();
            }

            // Go to login page
            this.login();
        },

        login: function () {
            if (this.loginView !== undefined) {
                this.loginView.destroy();
                delete this.loginView;
            }
            this.loginView = new LoginView({
                el: $('#AppContainer'),
                model: this.userAccountModel
            });
        },

        registration: function() {
            if (this.registrationView !== undefined) {
                this.registrationView.destroy();
                delete this.registrationView;
            }
            this.registrationView = new RegistrationView({
                el: $('#AppContainer'),
                model: this.registrationModel
            });
        }
    });
});