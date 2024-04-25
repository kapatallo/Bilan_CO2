import React, { useState, useEffect } from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import './FoodSearchComponent.css';


const DiversSearchComponent = ({ onItemSelected }) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedItem, setSelectedItem] = useState(null);
  const [quantity, setQuantity] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  
  useEffect(() => {
    if (searchTerm.length > 3) {
      fetchSuggestions(searchTerm);
    } else {
      setSuggestions([]);
    }
  }, [searchTerm]);
  
  const fetchSuggestions = async (search) => {
    const searchLower = search.toLowerCase();
    const searchCapitalized = search.charAt(0).toUpperCase() + search.slice(1);
    
    const encodedSearchTerm = encodeURIComponent(`(*${searchLower}* OR *${searchCapitalized}*)`);
    
    const url = `https://data.ademe.fr/data-fair/api/v1/datasets/base-carboner/lines?page=1&after=1&size=12&sort=&select=Identifiant_de_l'%C3%A9l%C3%A9ment,Nom_base_fran%C3%A7ais,Nom_attribut_fran%C3%A7ais,Code_de_la_cat%C3%A9gorie,Unit%C3%A9_fran%C3%A7ais,Total_poste_non_d%C3%A9compos%C3%A9&q_fields=Nom_base_fran%C3%A7ais,Nom_base_anglais&qs=Code_de_la_cat%C3%A9gorie: Achats de biens AND Nom_base_fran%C3%A7ais:${encodedSearchTerm}
`;
    try {
      const response = await fetch(url);
      if (response.ok) {
        const data = await response.json();
        console.log(data)
        setSuggestions(data.results.map(item => ({
          ...item, 
          key: item["Identifiant_de_l'élément"],
          label: item.Nom_attribut_français 
          ? `${item.Nom_base_français} (${item.Nom_attribut_français})`
          : item.Nom_base_français
        })));
      } else {
        console.error('Response not ok', response);
        setSuggestions([]);
      }
    } catch (error) {
      console.error('Erreur lors de la récupération des suggestions:', error);
      setSuggestions([]);
    }
  };
  // Helper function to filter duplicates in real-time based on the label
  const filterOptions = (options) => {
    const seenLabels = new Set();
    return options.filter(option => {
      if (seenLabels.has(option.label)) {
        return false;
      } else {
        seenLabels.add(option.label);
        return true;
      }
    });
  };
  
  const handleQuantityChange = (event) => {
    setQuantity(event.target.value);
  };
  
  const handleAddItem = () => {
    if (selectedItem && quantity) {
      onItemSelected({ ...selectedItem, quantity });
    }
  };
  
  return (
    <div className='food-search'>
    <Autocomplete
    disablePortal
    id="food-search"
    options={filterOptions(suggestions)}
    getOptionLabel={(option) => option.label || ""}
    renderInput={(params) => <TextField {...params} label="Ajouter un achat" />}
    onInputChange={(event, newInputValue) => {
      setSearchTerm(newInputValue);
    }}
    onChange={(event, newValue) => {
      setSearchTerm(newValue ? newValue.label : '');
      setSelectedItem(newValue);
    }}
    freeSolo
    />
    <div style={{ marginTop: '10px' }}>
    <TextField
    id="quantity"
    label="Quantité"
    type="number"
    value={quantity}
    onChange={handleQuantityChange}
    InputProps={{
      endAdornment: <span> Unt(s) </span>
    }}
    style={{ marginRight: '10px' }}
    disabled={!selectedItem} 
    />
    <Button
    id = "ajouter-food"
    variant="contained"
    onClick={handleAddItem}
    disabled={!selectedItem || !quantity}
    sx={{
      backgroundColor: '#7A40F2', 
      color: '#ffffff', 
      '&:hover': {
        backgroundColor: '#4b2b8b' 
      }
    }}
    >
    Ajouter
    </Button>
    </div>
    </div>
  );
};

export default DiversSearchComponent;