import React, {Component, Fragment} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Home extends Component {
	constructor() {
		super();
		this.state = {list: []};
	}

	componentDidMount() {
		axios.get("/api/artist/get/example")
		.then(response => {
			const data = response.data;
			if (Array.isArray(data) &&  data.length) {
				this.setState({list: data});
			} else {
				this.setState({status: "Something went wrong."});
			}
		}).catch(error => {
			console.log(error);
		});
	}

	render() {
		return(
			<Fragment>
				<div className="home-body">
					<div className="jumbotron jumbotron-fluid bg-dark m-0 text-danger">
						<h1 className="display-1 text-center user-select-none">Mocking Recording Label</h1>
						<p className="lead text-center user-select-none">Home to many of your favourite rappers and artists</p>
					</div>
					<div className="jumbotron jumbotron-fluid bg-dark p-0 m-0">
						<h1 className="display-4 text-center text-danger">Some of our talents include:</h1>
						<ul className="list-group list-group-horizontal justify-content-center">
							{this.state.list.map((artist, i) => (
								<li key={i} className="list-group-item bg-dark" style={{width: 300}}>
									<Link to={"/artist/" + artist.artist_id}>
										<img src={"data:image/png;base64," + artist.image} alt={artist.name} style={{width:250}}/>
										<h5 className="text-center text-danger">{artist.name}</h5>
									</Link>
								</li>
							))}
						</ul>
						<p className="text-center m-0 p-1"><a href="/artists">View More Artists From Us</a></p>
					</div>
					<div className="display-4 text-center bg-dark p-5 text-danger">
						<p>Mocking is a major record label that is mostly 
						focussed on rap music. We manage loads of different 
						artists and therefore are quite a large company whose 
						image is changing constantly. Each new artist adds something 
						new to the label and we always try and encourage our 
						musicians to really push forward with whatever makes them 
						stand out.</p>
						<a href="/about-mocking" className="lead">Learn More About Us</a>
					</div>
				</div>
			</Fragment>
		);
	}
}

export default Home;