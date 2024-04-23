import PageButton from "./PageButton.jsx"

export default function NavBar() {
    return (
        <div>
            <PageButton pageName="Dashboard" />
            <PageButton pageName="Comparaison"/>
            <PageButton pageName="Settings"/>
            <PageButton pageName="Déconnexion"/>
        </div>
    )
}