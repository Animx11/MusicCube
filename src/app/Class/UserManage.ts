import { Role } from './role';

export class UserManage {

    id: number;
    userName: string;
    email: string;
    roles: Role[];
    primaryRole: string;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.userName = (obj && obj.userName) || '';
        this.email = (obj && obj.email) || '';
        this.roles = (obj && obj.roles) || null;
        this.primaryRole = (obj && obj.primaryRole) || '';
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

  getRoles(): Role[] {
    return this.roles;
  }

  getPrimaryRole(): string {
    return this.primaryRole;
  }


  /** Setters */

  setUserName(value: string) {
    this.userName = value;
  }

  setEmail(value: string) {
    this.email = value;
  }

  setRoles(value: Role[]) {
    this.roles = value;
  }

  setPrimaryRole(primaryRole: string) {
    this.primaryRole = primaryRole;
  }


}
