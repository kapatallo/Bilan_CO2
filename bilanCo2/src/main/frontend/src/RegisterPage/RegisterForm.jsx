export default function ConnectionForm() {
    return (
        <form>
            <label for="prenom">Prénom:</label>
            <input type="text" id="prenom" name="prenom"/>
            
            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom"/>

            <label for="email">Email:</label>
            <input type="text" id="email" name="email"/>

            <label for="pwd">Mot de passe:</label>
            <input type="text" id="pwd" name="pwd"/>

            <label for="confirmedPwd">Confirmer le mot de passe:</label>
            <input type="text" id="confirmedPwd" name="confirmedPwd"/>

            <input type="submit" value="S'inscrire" />
        </form>
    )
}