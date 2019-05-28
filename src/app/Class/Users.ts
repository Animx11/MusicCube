export class Users {
  id: number;
  private userName: string;
  private password: string;
  private email: string;
  private roles: string[];
  private firstName: string;
  private lastName: string;
  private birthDate: Date;
  private aboutUser: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.userName = (obj && obj.userName) || '';
    this.email = (obj && obj.email) || '';
    this.roles = (obj && obj.roles) || null;
    this.birthDate = (obj && obj.birthDate) || null;
    this.firstName = (obj && obj.firstName) || '';
    this.lastName = (obj && obj.lastName) || '';
    this.aboutUser = (obj && obj.aboutUser) || '';
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

  getFirstName(): string {
    return this.firstName;
  }

  getLastName(): string {
    return this.lastName;
  }

  getBirthDate(): Date {
    return this.birthDate;
  }

  getAboutUser(): string {
    return this.aboutUser;
  }

  /** Setters */

  setUserName(value: string) {
    this.userName = value;
  }

  setPassword(value: string) {
    this.password = value;
  }

  setEmail(value: string) {
    this.email = value;
  }

  setRoles(value: string[]) {
    this.roles = value;
  }

  setFirstName(value: string) {
    this.firstName = value;
  }

  setLastName(value: string) {
    this.lastName = value;
  }

  setBirthDate(value: Date) {
    this.birthDate = value;
  }

  setAboutUser(value: string) {
    this.aboutUser = value;
  }
}
