import api from './api'


class UserService {

    static login(userObj){

        var querystring = require('querystring');
        return api.post(`/login`, querystring.stringify({
            username: userObj.username, //gave the values directly for testing
            password: userObj.password
        }));
    }

    static getUser(userId){
        return api.get(`/users/${userId}`);
    }

    static updateUser(userId, user){
        return api.put(`/users/${userId}`, user);
    }
    
    static deleteUser(userId){
        return api.delete(`/users/${userId}`);
    }
}

export default UserService;
