export class UserAccount {

    private userName: string;
    private password: string;
    private email: string;

    constructor(obj?: any) {
      this.userName = (obj && obj.userName) || '';
      this.password = (obj && obj.password) || '';
      this.email = (obj && obj.email) || '';
    }

    // Getters

    getUserName(): string {
      return this.userName;
    }

    getPassword(): string {
      return this.password;
    }

    getEmail(): string {
      return this.email;
    }

    // Setters

    setUserName(value: string) {
      this.userName = value;
    }

    setPassword(value: string) {
      this.password = value;
    }

    setEmail(value: string) {
      this.email = value;
    }

}
