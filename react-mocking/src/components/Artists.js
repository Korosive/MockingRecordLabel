import React, {Component, Fragment} from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class Artists extends Component {
	constructor() {
		super();
		this.state = {list: []};
	}

	componentDidMount() {
		axios.get("/api/artist/get/list")
		.then(response => {
			const data = response.data;
			if (Array.isArray(data) && data.length) {
				this.setState({list: data});
			} else {
				this.setState({status: "Invalid data response"});
			}
		}).catch(error => {
			this.setState({status: error.message});
		});
	}

	render() {
		var listArtist;
		if (this.state.list.length) {
			listArtist = this.state.list.map((artist, i) => (
				<div key={i} className="col p-1 border">
					<Link to={"/artist/" + artist.artist_id}>
						<img src={"data:image/png;base64," + artist.image} alt={artist.name} style={{width:250}}/>
						<p className="text-center">{artist.name}</p>
					</Link>
				</div>
			));
		}
		return(
			<Fragment>
				{this.state.status && 
					<div>
						<p>{this.state.status}</p>
					</div>
				}
				<h1 className="display-1 text-center bg-dark p-5">Artists</h1>
				<div className="container p-2">
					<div className="row">
						{listArtist}
					</div>
				</div>
			</Fragment>
		);
	}
}

export default Artists;