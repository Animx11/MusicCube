export class UserManage {

    id: number;
    userName: string;
    email: string;
    roles: string[];

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.userName = (obj && obj.userName) || '';
        this.email = (obj && obj.email) || '';
        this.roles = (obj && obj.roles) || null;
      }

        /** Getters */

  getId(): number {
    return this.id;
  }

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
