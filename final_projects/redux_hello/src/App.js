import React, {Component} from 'react';
import PostForm from "./PostForm";
import AllPost from "./AllPost";

class App extends Component {
  render() {
    return (
      <div className="App">
        <h2 className="center ">Post It</h2>
        <PostForm/>
        <AllPost/>
      </div>
    );
  }
}

export default App;