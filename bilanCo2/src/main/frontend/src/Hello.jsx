import { useEffect, useState } from "react";

export default function Hello() {
    const [data,setData] = useState(false);
    
    useEffect(() => {
        fetch("localhost:8080/HelloWorld", //On cherche la ressource à cette adresse
        {
            method:"GET",
            mode:"same-origin",
            headers: {
                "Content-Type": "application/json",
            }

        })
            .then(response => {
                if(response.ok) {
                    return response.json()
                }
                throw response
            })
            .then(data => {
                setData(data)
            })
            .catch(error => {
                console.error("Error fetching Hello :",error);
            })
    },[])

    

    return (
      <div>  </div>
    );
  }