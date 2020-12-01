import React, {Component, Fragment} from 'react';
import {Link} from 'react-router-dom';

class Navigation extends Component {
	render() {
		return(
			<Fragment>
				<nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow bg-dark fixed-top">
					<Link to="/" className="navbar-brand text-danger">Mocking Record Label</Link>
					<ul className="navbar-nav mr-auto">
						<li className="nav-item">
							<Link to ="/" className="nav-link text-danger">Home</Link>
						</li>
						<li className="nav-item">
							<Link to="/artists" className="nav-link text-danger">Artists</Link>
						</li>
						<li className="nav-item">
							<Link to="/about-mocking" className="nav-link text-danger">About Mocking</Link>
						</li>
						<li className="nav-item">
							<Link to="/book-studio" className="nav-link text-danger">Book Our Studio</Link>
						</li>
					</ul>
				</nav>
			</Fragment>
		);
	}
	
}

export default Navigation;