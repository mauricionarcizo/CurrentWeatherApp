(function() {
    'use strict';
    var app = angular.module('app', ['restangular']);

    app.config(['RestangularProvider', function(RestangularProvider) {
        var baseUrl = 'http://localhost:8080/AppPrevisaoTempo/rs/previsaoTempo';
        RestangularProvider.setBaseUrl(baseUrl);
    }]);

    app.$injector = ['$scope', '$http', '$q', 'Restangular'];
    app.controller('AppCtrl', AppCtrl);

    function AppCtrl($scope, $http, $q, Restangular) {
        var vm = this;
        vm.cidades = [];
        vm.pesquisarPrevisaoTempo = _pesquisarPrevisaoTempo;

        init();

        function init() {
            $q.all([obterCidades()]);
        }

        function _pesquisarPrevisaoTempo() {
            Restangular.one(vm.cidadeSelecionada)
                .get()
                .then(function(result) {
                    vm.previsaoTempo = result;
                });
        }

        function obterCidades() {
            Restangular.all('cidades').getList()
                .then(function(data) {
                    angular.forEach(data, function(cidade) {
                        vm.cidades.push({
                            'cidade': cidade.cidade
                        });
                    });
                    vm.cidadeSelecionada = 'Florianopolis Aeroporto';
                });

        }
    }


})();
