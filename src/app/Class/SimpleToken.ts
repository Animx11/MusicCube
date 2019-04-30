export class SimpleToken {

    private id: number;
    private userName: string;
    private code: string;
    private userPermission: string;
    private expiryTime: Date;

    constructor(obj?: any) {
        this.userName = (obj && obj.userName) || '';
        this.code = (obj && obj.code) || '';
        this.userPermission = (obj && obj.userPermission) || '';
        this.expiryTime = (obj && obj.expiryTime) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getUserName(): string {
        return this.userName;
    }

    getCode(): string {
        return this.code;
    }

    getUserPermission(): string {
        return this.userPermission;
    }

    getExpiryTime(): Date {
        return this.expiryTime;
    }

    // Setters

    setUserName(userName: string) {
        this.userName = userName;
    }

    setCode(code: string) {
        this.code = code;
    }

    setUserPermission(userPermission: string) {
        this.userPermission = userPermission;
    }

    setExpiryTime(expiryTime: Date) {
        this.expiryTime = expiryTime;
    }

}
