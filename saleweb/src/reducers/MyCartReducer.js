const MyCartReducer = (currentState, action) => {
    if (action.type === 'update')
        return currentState + action.payload;

    return currentState;
}

export default MyCartReducer;