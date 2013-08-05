angular.module('history', []).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/:contact', { templateUrl: 'history.html', controller: HistoryController }).
      when('/tx/:id', { templateUrl: 'details.html', controller: DetailsController }).
      otherwise({redirectTo: '/'});
}]);

function ContactsController($scope) {
	$scope.contacts = [
		{ name: "Alan" }, 
		{ name: "Bob" }, 
		{ name: "Mike" }, 
		{ name: "George" }
	];
}

txs = { 
	Alan: [
		{ id: 1, title: "Alan 1", amount: 1000 },
		{ id: 2, title: "Alan 2", amount: 1000 },
		{ id: 3, title: "Alan 3", amount: 3000 }
	],
	Bob: [
		{ id: 1, title: "Bob 1", amount: 1000 },
		{ id: 2, title: "Bob 2", amount: 1000 },
		{ id: 3, title: "Bob 3", amount: 3000 }
	],
	Mike: [
		{ id: 1, title: "Mike 1", amount: 1000 },
		{ id: 2, title: "Mike 2", amount: 1000 },
		{ id: 3, title: "Mike 3", amount: 3000 }
	],
	George: [
		{ id: 1, title: "George 1", amount: 1000 },
		{ id: 2, title: "George 2", amount: 1000 },
		{ id: 3, title: "George 3", amount: 3000 }
	]
};

function HistoryController($scope, $routeParams) {
	$scope.txs = txs[$routeParams.contact];
}

function DetailsController($scope, $routeParams) {
	$scope.txId = $routeParams.id;
	$scope.tx = { id: 1, type: "P2P", amount: 2000, title: "Just a tx details" };
}