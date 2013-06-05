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
		clearSelection();
		$("#txs").addClass("active");
		$("#main-content").html(Views.txsView.render({
			transactions: [{
				contact: "Andrew",
				message: "My debt",
				amount: "1000 RUB",
				status: "SUCCEEDED",
				date: 1231231122312
			}, {
				contact: "Alex",
				message: "It's for my beer",
				amount: "500 RUB",
				status: "PENDING",
				date: 1231841122312
			}, {
				contact: "Mike",
				message: "Happy birthday!",
				amount: "2000 RUB",
				status: "FAILED",
				date: 1281231132312
			}]
		}).$el);
	},
	newPayment: function() {
		clearSelection();
		$("#main-content").html(Views.newPaymentView.render({}).$el);
	}
});

new HomeController();
Backbone.history.start();