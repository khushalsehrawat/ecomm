# 🛒 Mini Shopping Cart Project (Full Explanation)

This is a beginner-friendly, step-by-step explained **Mini Shopping Cart Web App** made using just HTML and JavaScript. It's built in a way that **anyone can understand in one go**, even with **zero coding background**.

---

## 📁 Project Files Breakdown

### 1. `index.html`
- This is your **home page** where all the products are displayed.
- It includes a reference to `script.js` which holds all the logic.

#### Key Sections:
- `<div id="product-list"></div>`: This empty div will be filled using JavaScript with product cards.
- `<a href="cart.html">Go to Cart</a>`: A link to view the cart.

---

### 2. `cart.html`
- This is your **cart page** where all added products are shown.
- It uses the same `script.js` logic.

#### Key Sections:
- `<div id="cart-list"></div>`: This gets filled with the cart items using JavaScript.
- `<a href="index.html">Back to Products</a>`: Navigate back to products.

---

### 3. `script.js`
This is the brain of the whole project. It does everything from showing products to managing the cart.

---

### 🚀 Features & Detailed Code Explanation

---

### ✅ Product List Simulation

```js
const products = [
  { id: 1, name: "Laptop", price: 75000, imageUrl: "https://via.placeholder.com/150" },
  { id: 2, name: "Phone", price: 50000, imageUrl: "https://via.placeholder.com/150" },
  { id: 3, name: "Headphones", price: 5000, imageUrl: "https://via.placeholder.com/150" }
];
```

- This array **simulates a backend API**. Each product has:
  - `id`: unique identifier.
  - `name`, `price`, `imageUrl`: product details.

---

### ✅ Render Products on `index.html`

```js
if (document.title === "Mini Cart") {
  const productList = document.getElementById("product-list");

  products.forEach((product) => {
    const card = document.createElement("div");
    card.className = "product-card";
    card.innerHTML = \`
      <img src="\${product.imageUrl}" alt="\${product.name}" width="100" />
      <h3>\${product.name}</h3>
      <p>₹\${product.price}</p>
      <button onclick="addToCart(\${product.id})">Add to Cart</button>
    \`;
    productList.appendChild(card);
  });
}
```

- `document.title === "Mini Cart"`: Checks if we are on the products page.
- `productList`: Refers to the `<div>` in HTML.
- `products.forEach`: Loops through each product and creates a "card".
- `addToCart(product.id)`: Adds the product to the cart using the ID.

---

### ✅ Add to Cart Logic

```js
function addToCart(productId) {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const item = products.find(p => p.id === productId);
  cart.push(item);
  localStorage.setItem("cart", JSON.stringify(cart));
  alert("Added to cart!");
}
```

- `localStorage`: Built-in browser storage. It **remembers your cart even if you refresh**.
- `JSON.parse(...)`: Converts string data to an array.
- `products.find(...)`: Finds the product using its ID.
- `cart.push(item)`: Adds item to cart.
- `localStorage.setItem(...)`: Saves the updated cart.
- `alert(...)`: Confirms to user that product is added.

---

### ✅ Show Cart on `cart.html`

```js
if (document.title === "Your Cart") {
  const cartList = document.getElementById("cart-list");
  const cart = JSON.parse(localStorage.getItem("cart")) || [];

  cart.forEach((item, index) => {
    const div = document.createElement("div");
    div.innerHTML = \`
      <h3>\${item.name}</h3>
      <p>₹\${item.price}</p>
      <button onclick="removeFromCart(\${index})">Remove</button>
      <hr>
    \`;
    cartList.appendChild(div);
  });
}
```

- Gets the saved cart from `localStorage`.
- Loops through each product.
- Adds a "Remove" button for each item.

---

### ✅ Remove Item from Cart

```js
function removeFromCart(index) {
  const cart = JSON.parse(localStorage.getItem("cart"));
  cart.splice(index, 1); // removes item at that position
  localStorage.setItem("cart", JSON.stringify(cart));
  location.reload(); // refresh to update UI
}
```

- `splice(index, 1)`: Removes one item from cart based on its position.
- Updates the cart in `localStorage`.
- `location.reload()`: Refreshes the page to update the view.

---

## 🔁 Why We Use These?

- **Event Listeners (`onclick`)**: To perform an action when user clicks a button.
- **Splice**: To remove specific items.
- **Dynamic HTML (`innerHTML`)**: To insert content into a page from JS.
- **localStorage**: To save data in browser without backend.
- **Array Methods (`forEach`, `find`)**: To loop and search through data.

---

## 🧠 What You Learn (Core Concepts)

- HTML + JavaScript Project Structure
- DOM Manipulation
- Data Handling using localStorage
- Project Thinking (divide code by logic)
- Building Features from Scratch

---

## ✅ How to Use This Project

1. Download and unzip.
2. Open `index.html` in browser.
3. Click **Add to Cart**.
4. Go to `cart.html` to view or remove items.
5. That's it — fully working!

---

This project is your **first step toward bigger e-commerce apps**. Keep experimenting and upgrading it!

Happy Coding 💡🚀


🌐 Visit my web development studio — [TheVB24.com](https://www.thevb24.com)

****


## Website metadata

The two static HTML pages in `Ecomm-Ui/` include page-specific descriptions, author/creator metadata retaining TheVB24 alongside TheBenzzCreations, Open Graph metadata and Twitter card metadata. Existing titles, DuskyDawn branding, application code and the original TheVB24 footer/README credits are preserved.

Production identity is recorded in `seo-sites.json`; see Canonical URL validation below. The developer credit is not evidence of a project deployment URL.

One homepage JSON-LD block describes the website and its credited creators. No product, price, inventory, review or rating claims have been added. One visible developer link appears in the existing homepage footer with a normal same-tab hyperlink for the developer credit. No dedicated social preview image is configured. Structured data does not guarantee search rich results.

The UI has no package manifest, configured lint suite or build command. Backend compilation targets Java 21; product and ordering operations depend on the separate Spring Boot API and configured database.

## Development & Maintenance

**Developed & Maintained by
[Khushal Sehrawat](https://github.com/khushalsehrawat)**
— Founder of
[TheBenzzCreations](https://www.thebenzzcreations.com/).

TheBenzzCreations is a digital development studio specializing in website development, custom software, mobile applications, e-commerce solutions and AI-powered business automation.

For development enquiries and collaborations, visit [TheBenzzCreations](https://www.thebenzzcreations.com/).

The existing TheVB24 design credit is preserved.

## Canonical URL validation

Run `python scripts/validate_seo.py` from this repository before publishing. The dependency-free check validates exact page canonicals, Open Graph URLs, website/application JSON-LD URLs, sitemap locations, robots sitemap references and literal framework site metadata against `seo-sites.json`. It checks deployment path prefixes as well as domains, so one GitHub Pages project cannot canonicalize to another.

Only URLs whose live HTML matched the local page on 2026-11-21 are configured. Unverified pages have no canonical or social URL; `python scripts/validate_seo.py --require-verified` also fails for those pending pages. Reverify the configuration if the deployment moves. Dynamic framework expressions still require runtime validation.

TheBenzzCreations attribution, creator organization URLs and existing normal same-tab developer credit links are preserved. These outbound attribution links are separate from site identity.
