var clearSelection = function() {
	$(".top-bar-section .active").removeClass("active");
};

var HomeController = Backbone.Router.extend({
	routes: {
		"contacts": "contacts",
		"txs": "transactions",
		"new-payment": "newPayment"
	},
	contacts: function() {
		clearSelection();
		$("#contacts").addClass("active");
	},
	transactions: function() {
		var tx = new Transaction();
		tx.fetch({success: function(data) {
			clearSelection();
			$("#txs").addClass("active");
			$("#main-content").html(Views.txsView.render(data.attributes).$el);
		}});
	},
	newPayment: function() {
		clearSelection();
		$("#main-content").html(Views.newPaymentView.render({}).$el);
	}
});

var homeController = new HomeController();
Backbone.history.start();