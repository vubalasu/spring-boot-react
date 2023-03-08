import React, {Component} from 'react'
import Select from 'react-select'
import axios from 'axios'


export default class App extends Component {

    constructor(props) {
        super(props)
        this.onChangeName = this.onChangeName.bind(this);
        this.setGender = this.setGender.bind(this);
        this.onChangeAge = this.onChangeAge.bind(this);
        this.saveUser = this.saveUser.bind(this);
        this.state = {
            selectOptions: [],
            id: "",
            name: '',
            sex: "",
            age: "",
            country: "",
            submitted: false
        }
    }

    async getOptions() {
        const res = await axios.get('http://localhost:3003/countries')
        const data = res.data

        let options = data.map(d => ({
            "value": d.alpha2Code,
            "label": d.name

        }))

        this.setState({selectOptions: options})

    }

    handleChange(e) {
        console.log(e)
        this.setState({country: e.label})
    }

    componentDidMount() {
        this.getOptions()
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeAge(e) {
        this.setState({
            age: e.target.value
        });
    }

    setGender(e) {
        this.setState({
            sex: e.target.value
        });
    }

    saveUser() {
        var data = {
            name: this.state.name,
            age: this.state.age,
            sex: this.state.sex,
            country: this.state.country
        };

        axios.post("http://localhost:8080/api/users", data,
            {
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => {
                if(response.status === 201){
                    this.setState({
                        id: response.data.id,
                        name: response.data.name,
                        age: response.data.age,

                        submitted: true
                    });
                    alert("user registered " + response.data.name)
                } else{
                    alert("user error " + response.data)
                }
            })
            .catch(e => {
                alert("user error " + e)
            });
    }

    render() {
        console.log(this.state.selectOptions)
        return (
            <div style={{marginTop: '1em'}}>
                <div className="userElement" style={{marginBottom: '1em', marginLeft: '5em'}}>
                    <label htmlFor="name" style={{marginRight: '5em'}}>Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        required
                        value={this.state.name}
                        onChange={this.onChangeName}
                        name="name"
                    />
                </div>
                <div className="userElement" style={{marginBottom: '1em', marginLeft: '5em'}}>
                    <label style={{marginRight: '5em'}}>Sex</label>
                    <input type="radio"
                           onClick={this.setGender} value="male"/> Male
                    <input type="radio"
                           onClick={this.setGender} value="female"/> Female
                </div>
                <div className="userElement" style={{marginBottom: '1em', marginLeft: '5em'}}>
                    <label htmlFor="age" style={{marginRight: '5em'}}>Age</label>
                    <input
                        type="text"
                        className="form-control"
                        id="age"
                        required
                        value={this.state.age}
                        onChange={this.onChangeAge}
                        name="age"
                    />
                </div>
                <div className="userElement" style={{marginBottom: '1em', marginLeft: '5em'}}>
                    <label htmlFor="country">Country</label>
                    <Select id="country" options={this.state.selectOptions} onChange={this.handleChange.bind(this)}/>
                </div>

                <div className="createAccount" style={{marginBottom: '1em', marginLeft: '5em'}}>
                    <button onClick={this.saveUser} className="btn btn-success">
                        Submit
                    </button>
                </div>


            </div>
        )
    }
}

