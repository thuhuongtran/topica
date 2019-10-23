import React                        from 'react';
import { connect }                  from 'react-redux';
import { Redirect }                 from 'react-router-dom';

class PrivateComponent extends React.Component {
    constructor(props){
        super(props);
    }

    render(){
        
        if(!this.props.userInfo){
            return <Redirect to={{ pathname: '/login' }} />
        }

        if ((this.props.roles && this.props.roles.indexOf(this.props.userInfo.role) === -1) && (this.props.userIds && this.props.userIds.indexOf(this.props.userInfo.id) === -1) ) {
            return <Redirect to={{ pathname: '/'}} />
        }

        return(
            <>
                {this.props.children}
            </>  
        )
    }
}

function _mapStateToPropsTop(state) {
	return {
        userInfo: state['xtool/app'].userInfo
	};
}
export default connect(_mapStateToPropsTop)(PrivateComponent);
