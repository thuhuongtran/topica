import React                        from 'react';
import { connect }                  from 'react-redux';

class Admin extends React.Component {
    constructor(props){
        super(props);
    }

    render(){
        return(
            <p>Admin</p>
        )
    }
}

function _mapStateToPropsTop(state) {
	return {
	};
}
export default connect(_mapStateToPropsTop)(Admin);
