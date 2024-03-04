const addOneItem = (state, todoItem) => {
    let obj = {completed: false, item: todoItem};
    localStorage.setItem(todoItem, JSON.stringify(obj));
    state.todoItems.push(obj)
}

const removeOneItem = (state, payload)  => {
    localStorage.removeItem(payload.todoItem.item);
    state.todoItems.splice(payload.index, 1); //배열을 변경해서 새로 넣어줌
}

const toggleOneItem = (state, payload)  => {
    state.todoItems[payload.index].completed = !state.todoItems[payload.index].completed // to-be
    localStorage.removeItem(payload.todoItem.item);
    localStorage.setItem(payload.todoItem.item, JSON.stringify(payload.todoItem));
}

const clearAllItems = (state)  => {
    localStorage.clear();
    state.todoItems = [];
}

// 방법2
export { addOneItem, clearAllItems, removeOneItem, toggleOneItem }