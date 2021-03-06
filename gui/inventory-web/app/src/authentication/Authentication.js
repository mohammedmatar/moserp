import LoginController from 'authentication/LoginController'
import AuthenticationService    from 'authentication/AuthenticationService'
import 'angular-ui-router'

let moduleName = angular
    .module("authentication", ['structure', 'ui.router'])
    .service("AuthenticationService", AuthenticationService)
    .controller("LoginController", LoginController)
    .directive('appLogin', function () {
        return {
            templateUrl: "src/authentication/view/login.html",
            controller: LoginController
        };
    })
    .name;

export default moduleName;
