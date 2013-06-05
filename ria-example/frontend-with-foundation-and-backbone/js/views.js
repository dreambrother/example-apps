var TransactionsView = Backbone.View.extend({
	template: _.template($('#txs-template').html()),
    render: function (model) {
        this.$el.html(this.template(model));
        return this;
    }
});

var ContactsView = Backbone.View.extend({
	template: _.template($('#contacts-template').html()),
    render: function (model) {
        this.$el.html(this.template(model));
        return this;
    }
});

var views = {
	txsView: new TransactionsView(),
	contactsView: new ContactsView()
};