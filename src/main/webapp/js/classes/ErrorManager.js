/**
 * An ErrorManager
 */
define([], function () {
    /**
     * Needed for unit testing
     */
    var htmlElement = $('#ErrorManagerTemplate').html();
    if (!htmlElement) {
        htmlElement = '<div>needed for unit testing</div>'
    }

    var errorTemplate = _.template(htmlElement);

    var ErrorManager = {
        VALIDATION_NOT_EMPTY: "not_empty",
        getInstance: function (options) {
            // Get elements
            this.$containerEl = options.$containerEl;
            this.$errorEl = options.$errorEl;

            // Get validations
            this.validations = (options.validations !== undefined) ? options.validations : [];

            // Methods
            this.validate = function (options) {
                // Init
                var errors = [];
                var successCallback = options.success;
                var failureCallback = options.failure;

                // loop through inputs
                var inputs = this.$containerEl.find('input[type=text], input[type=password]');
                for (var inputIndex = 0; inputIndex < inputs.length; inputIndex++) {
                    var $input = $(inputs[inputIndex]);
                    for (var nameIndex = 0; nameIndex < this.validations.length; nameIndex++) {
                        var validationName = this.validations[nameIndex];
                        var message = (getValidation(validationName))($input);
                        if (message !== undefined) {
                            var name = $input.attr("name");
                            $input.addClass('inError');
                            errors.push(name + message);
                        }
                    }
                }

                // Call the appropriate function
                if (errors.length === 0) {
                    if (successCallback !== undefined) {
                        successCallback();
                    }
                } else {
                    // Display errors
                    this.$errorEl.append(errorTemplate({
                        errors: errors
                    }));
                    this.$errorEl.show(500);

                    // Execute callback
                    if (failureCallback !== undefined) {
                        failureCallback();
                    }
                }
            };
            this.reset = function () {
                this.$errorEl.hide(500);
                this.$errorEl.empty();
                this.$containerEl.find('.inError').removeClass('inError');
            };

            return this;
        }
    };

    var MESSAGES = {
        NOT_EMPTY: ": This field should not be blank"
    };

    function validation_notEmpty($input) {
        if ($input.val() === '') {
            return MESSAGES.NOT_EMPTY;
        }
    }

    function getValidation(validationName) {
        var ret;
        switch (validationName) {
            case ErrorManager.VALIDATION_NOT_EMPTY:
                ret = validation_notEmpty;
                break;
            default:
                ret = function () {
                    return true;
                };
        }
        return ret;
    }

    return ErrorManager;
});