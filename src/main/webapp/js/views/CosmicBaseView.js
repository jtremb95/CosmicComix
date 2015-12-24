/* global Backbone */

define([], function () {
    return Backbone.View.extend({
        destroy: function () {
            this.unbind();
            this.undelegateEvents();
            this.$el.empty();
        },
        setHeader: function() {
            $();
        },
        setTitle: function() {
            
        }
    });
});