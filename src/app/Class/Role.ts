import { RoleName } from './RoleName';

export class Role {

    id: number;
    name: RoleName;

    constructor(obj?: any) {
        this.id = (obj && obj.id ) || 0;
        this.name = (obj && obj.name) || null;
    }

    getId(): number {
        return this.id
    }

    getName(): RoleName {
        return this.name;
    }

    setName(name: RoleName) {
        this.name = name;
    }

}
