import {NavLink} from 'react-router-dom'
import React, { Component } from 'react'
import {Button, Container, Menu, Responsive, Segment, Visibility,} from 'semantic-ui-react'
class NavBar extends Component {
    state = {}

    hideFixedMenu = () => this.setState({ fixed: false })
    showFixedMenu = () => this.setState({ fixed: true })

    render() {
        const { children } = this.props
        const { fixed } = this.state

        return (
            <Responsive {...Responsive.onlyComputer}>
                <Visibility once={false} onBottomPassed={this.showFixedMenu} onBottomPassedReverse={this.hideFixedMenu}>
                    <Segment inverted textAlign='center'>
                        <Menu
                            fixed={fixed ? 'top' : null}
                            inverted={!fixed}
                            pointing={!fixed}
                            secondary={!fixed}
                            size='medium'
                        >
                            <Container>
                                <Menu.Item as='a'>
                                    <NavLink exact to={'/'}>
                                        <p>Home</p>
                                    </NavLink>
                                </Menu.Item>
                                <Menu.Item as='a'>
                                    <NavLink exact to={'/watchlist'}>
                                        <p>Watchlist</p>
                                    </NavLink>
                                </Menu.Item>
                                <Menu.Item as='a'>
                                    <NavLink exact to={'/wallet'}>
                                        <p>Wallet</p>
                                    </NavLink>
                                </Menu.Item>
                                <Menu.Item as='a'>
                                    <NavLink exact to={'/profile'}>
                                        <p>Profile</p>
                                    </NavLink>
                                </Menu.Item>

                                <Menu.Item as='a' active>
                                    <NavLink exact to={'/trade'}>
                                        <p>Trade</p>
                                    </NavLink>
                                </Menu.Item>

                                <Menu.Item position='right'>
                                    <NavLink exact to={'/login'}>
                                        <Button as='a' inverted={!fixed}>Log in</Button>
                                    </NavLink>
                                    <NavLink exact to={'/register'}>
                                        <Button as='a' inverted={!fixed} primary={fixed} style={{ marginLeft: '0.5em' }}>Sign Up</Button>
                                    </NavLink>
                                </Menu.Item>
                            </Container>
                        </Menu>
                    </Segment>
                </Visibility>

                {children}
            </Responsive>
        )
    }
}



export default NavBar;
