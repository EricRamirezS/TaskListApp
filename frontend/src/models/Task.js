class Task {
    constructor(id, description, active, createdAt) {
        this.id = id;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.editMode = false;
    }
}

export default Task;