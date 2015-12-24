// constructor for Application object
function Application() {
    this.cosmicRouter = undefined;
}
var App = new Application();

requirejs(['CosmicRouter'],
    function (CosmicRouter) {
        App.cosmicRouter = new CosmicRouter();
        Backbone.history.start();
});