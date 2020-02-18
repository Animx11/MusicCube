import { RoleName } from './RoleName';

export class Role {

    id: number;
    name: string;

    constructor(obj?: any) {
        this.id = (obj && obj.id ) || 0;
        this.name = (obj && obj.name) || null;
    }

    getId(): number {
        return this.id
    }

    getName(): string {
        return this.name;
    }

    setName(name: string) {
        this.name = name;
    }

}
