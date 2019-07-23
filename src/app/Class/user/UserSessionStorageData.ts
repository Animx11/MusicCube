export class UserSessionStorageData {

    private userName: string;
    private email: string;
    private roles: string[];

    constructor(obj?: any) {
        this.userName = (obj && obj.userName) || '';
        this.email = (obj && obj.email) || '';
        this.roles = (obj && obj.roles) || null;
    }

    /** Getters */

    getUserName(): string {
        return this.userName;
    }
    getEmail(): string {
        return this.email;
    }

    getRoles(): string[] {
        return this.roles;
    }

    /** Setters */

    setUserName(value: string) {
        this.userName = value;
    }

    setEmail(value: string) {
        this.email = value;
    }

    setRoles(value: string[]) {
        this.roles = value;
    }

}
