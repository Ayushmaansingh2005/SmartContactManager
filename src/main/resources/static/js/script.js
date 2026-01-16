console.log("Script loaded");

let currentTheme = getTheme();

// get button first
const changeThemeButton = document.querySelector("#theme_change_button");

// apply saved theme on load
applyTheme(currentTheme);

// set initial button label (if button exists)
if (changeThemeButton) {
  const initialLabel = currentTheme === "dark" ? "Light" : "Dark";
  changeThemeButton.querySelector("span").textContent = initialLabel;

  changeThemeButton.addEventListener("click", () => {
    console.log("change theme button clicked");

    // toggle value
    currentTheme = currentTheme === "dark" ? "light" : "dark";

    // apply to DOM
    applyTheme(currentTheme);

    // save in localStorage
    setTheme(currentTheme);

    // update button text to opposite theme
    const nextLabel = currentTheme === "dark" ? "Light" : "Dark";
    changeThemeButton.querySelector("span").textContent = nextLabel;
  });
}

function applyTheme(theme) {
  const html = document.documentElement;
  html.classList.remove("light", "dark");
  html.classList.add(theme);
}

function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

function getTheme() {
  const theme = localStorage.getItem("theme");
  return theme === "dark" ? "dark" : "light";
}