export class UserProfile {

    private userName: string;
    private email: string;
    private firstName: string;
    private lastName: string;
    private birthDate: Date;
    private aboutUser: string;


  constructor(obj?: any) {
      this.userName = (obj && obj.userName) || '';
      this.email = (obj && obj.email) || '';
      this.firstName = (obj && obj.firstName) || '';
      this.lastName = (obj && obj.lastName) || '';
      this.birthDate = (obj && obj.birthDate) || null;
      this.aboutUser = (obj && obj.aboutUser) || '';
  }

  // Getters

  getUserName(): string {
    return this.userName;
  }

  getEmail(): string {
    return this.email;
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

  // Setters

  setUserName(value: string) {
    this.userName = value;
  }

  setEmail(value: string) {
    this.email = value;
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
