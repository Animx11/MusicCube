export class UserAccount {

    id: number;
    private userName: string;
    private password: string;
    private email: string;

    constructor(obj?: any) {
      this.id = (obj && obj.id) || 0;
      this.userName = (obj && obj.userName) || '';
      this.password = (obj && obj.password) || '';
      this.email = (obj && obj.email) || '';
    }

    // Getters

    getId(): number {
      return this.id;
    }

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
