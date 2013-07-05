var clearSelection = function() {
	$(".top-bar-section .active").removeClass("active");
};

var HomeController = Backbone.Router.extend({
	routes: {
		"txs": "transactions",
		"new-payment": "newPayment"
	},
	transactions: function() {
		var tx = new Transaction();
		tx.fetch({success: function(data) {
			clearSelection();
			$("#txs").addClass("active");
			$("#main-content").html(new TransactionsView().render(data.attributes).$el);
		}});
	},
	newPayment: function() {
		clearSelection();
		$("#main-content").html(new NewPaymentView().render({}).$el);
	}
});

var homeController = new HomeController();
Backbone.history.start();