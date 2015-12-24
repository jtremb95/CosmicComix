/* global _ */

define([
    'CosmicBaseView',
    'ErrorManager'
], function (CosmicBaseView, ErrorManager) {
    /**
     * Needed for unit testing
     */
    var htmlElement = $('#LoginPageMainTemplate').html();
    if (!htmlElement) {
        htmlElement = '<div>needed for unit testing</div>'
    }

    return CosmicBaseView.extend({
        template: _.template(htmlElement),

        events: {
            'click #login': 'loginValidate'
        },

        initialize: function () {
            // Call 
            
            // Render the page
            this.render();

            // Bind this to all callback functions
            _.bindAll(this, 'login');

            // Create a simple validator for input
            this.validator = ErrorManager.getInstance({
                $containerEl: this.$('#LoginInfoContainer'),
                $errorEl: this.$('#LoginError'),
                validations: [
                    ErrorManager.VALIDATION_NOT_EMPTY
                ]
            });
        },

        render: function () {
            this.$el.empty();
            this.$el.append(this.template);
        },

        loginValidate: function () {
            this.validator.reset();
            this.validator.validate({
                success: this.login
            });
        },

        login: function () {
            var username = this.$('#username').val();
            var password = this.$('#password').val();
            var userInfo = {
                username: username,
                password: password
            };
            this.model.save(userInfo, {
                success: function (model, success) {
                    if (success) {
                        // TODO router navigate
                    } else {
                        alert("The username/password combination you entered is incorrect.");
                    }
                }
            });
        }
    });
});