var clearSelection = function() {
	$(".top-bar-section .active").removeClass("active");
};

var HomeController = Backbone.Router.extend({
	routes: {
		"contacts": "contacts",
		"txs": "transactions"
	},
	contacts: function() {
		clearSelection();
		$("#contacts").addClass("active");
	},
	transactions: function() {
		clearSelection();
		$("#txs").addClass("active");
		$("#main-content").html(views.txsView.render({}).$el);
	}
});

var homeController = new HomeController();
Backbone.history.start();