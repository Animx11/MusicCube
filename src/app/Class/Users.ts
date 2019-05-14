export class Users {
  private id: number;
  private userName: string;
  private password: string;
  private email: string;
  private userPermission: string;
  private firstName: string;
  private lastName: string;
  private birthDate: Date;
  private aboutUser: string;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.userName = (obj && obj.userName) || "";
    this.password = (obj && obj.password) || "";
    this.email = (obj && obj.email) || "";
    this.userPermission = (obj && obj.userPermission) || "";
    this.birthDate = (obj && obj.birthDate) || null;
  }

  /** Getters */

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

  getUserPermission(): string {
    return this.userPermission;
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

  setUserPermission(value: string) {
    this.userPermission = value;
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
