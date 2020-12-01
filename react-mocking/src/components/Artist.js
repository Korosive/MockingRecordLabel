import React, {Component, Fragment} from 'react';
import axios from 'axios';
import {Redirect} from 'react-router-dom';

class Artist extends Component {
	constructor() {
		super();
		this.state = {};
	}

	componentDidMount() {
		const artist_id = this.props.match.params.id;
		if (artist_id) {
			axios.get("/api/artist/get/" + artist_id)
			.then(response => {
				const data = response.data;
				if (data) {
					this.setState({
						id: artist_id,
						image: data.image,
						name: data.name,
						description: data.description
					});
				} else {
					this.setState({invalid: true});
				}
			}).catch(error => {
				this.setState({invalid: true});
			})
		} else {
			this.setState({invalid: true});
		}
	}

	render() {
		if (this.state.invalid) {
			return(<Redirect to={{
				pathname: "/",
				state: {
					message: "Error in retrieving artist."
				}
			}}/>);
		}
		
		return(
			<Fragment>
				<h1 className="display-1 text-center bg-dark p-5 m-0">{this.state.name}</h1>
				<div className="media p-5 m-0 bg-dark">
					<img src={"data:image/png;base64," + this.state.image} className="mr-3" alt={this.state.name} style={{width: 350}}/>
					<div className="media-body">
						<p className="mt-0">{this.state.description}</p>
						<a href={"/book-artist/" + this.state.id}>Book {this.state.name}</a>
					</div>
				</div>
			</Fragment>
		);
	}
}

export default Artist;