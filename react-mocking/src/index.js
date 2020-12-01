import React from 'react';
import ReactDOM from 'react-dom';
import Navigation from './components/Navigation';
import Artists from './components/Artists';
import Artist from './components/Artist';
import Home from './components/Home';
import About from './components/About';
import BookStudio from './components/BookStudio';
import BookArtist from './components/BookArtist';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import * as serviceWorker from './serviceWorker';

const routing = (
	<Router>
		<Navigation />
		<Switch>
			<Route exact path="/" component={Home} />
			<Route exact path="/artists" component={Artists} />
			<Route path = "/artist/:id" component={Artist} />
			<Route exact path="/about-mocking" component={About} />
			<Route exact path="/book-studio" component={BookStudio} />
			<Route path="/book-artist/:id" component={BookArtist} />
		</Switch>
	</Router>
);

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
