# Vending Machine Simulator

A comprehensive Java vending machine simulator supporting both regular and special (combo) vending machines with a graphical user interface and transaction management system.

## Overview

This project implements a fully-functional vending machine system that simulates real-world operations including:
- Purchase transactions with money handling
- Inventory management for items and products
- Two distinct machine types with different capabilities
- Real-time GUI for user interaction
- Money denomination tracking and change calculation

## Features

### Machine Types
- **Regular Vending Machine**: Standard item vending with money insertion and withdrawal
- **Special Vending Machine**: Advanced machine supporting ingredient-based product creation (combo items)

### Core Functionality
- 💰 Multiple money denomination support (coins and bills)
- 📦 Configurable item and product slots
- 🔄 Complete transaction management (purchase, refund)
- 📊 Inventory tracking and restocking
- 🎯 Real-time GUI interface
- 💳 Change calculation and coin return

## Project Structure

```
VendingMachine/
├── README.md
├── VendingMachine.iml                 # IntelliJ project file
├── Model/
│   ├── VendingMachine.java           # Abstract base class for vending machines
│   ├── RegularVendingMachine.java     # Regular vending machine implementation
│   ├── SpecialVendingMachine.java     # Special vending machine with combo support
│   ├── Item.java                      # Represents individual items
│   ├── ItemSlot.java                  # Storage slot for items
│   ├── Product.java                   # Represents combinable products
│   ├── ProductSlot.java               # Storage slot for products
│   ├── MoneyDenominations.java        # Manages currency denominations
│   ├── Transaction.java               # Handles purchase transactions
│   └── VendingSim.java                # Console-based simulator
└── View/
    └── VendingMachineGUI.java         # GUI interface for the vending machine
```

## Installation & Setup

### Prerequisites
- Java 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE

### Steps

1. **Clone or extract the project**
   ```bash
   cd VendingMachine
   ```

2. **Compile the project**
   ```bash
   javac Model/*.java View/*.java
   ```

3. **Run the application**
   
   **GUI Mode:**
   ```bash
   java View.VendingMachineGUI
   ```
   
   **Console Simulator Mode:**
   ```bash
   java Model.VendingSim
   ```

## Usage

### GUI Interface
1. Launch the application using `VendingMachineGUI`
2. Create a vending machine (Regular or Special)
3. Configure item/product slots as needed
4. Use the interface to:
   - Insert money
   - Select items
   - Receive change
   - Manage inventory

### Console Simulator
1. Run `VendingSim` for interactive console-based testing
2. Follow the menu prompts to:
   - Create machines
   - Test purchasing
   - Manage inventory
   - Check transactions

## Key Classes

| Class | Purpose |
|-------|---------|
| `VendingMachine` | Abstract base class defining core vending machine behavior |
| `RegularVendingMachine` | Standard item dispensing machine |
| `SpecialVendingMachine` | Advanced machine supporting product combinations |
| `MoneyDenominations` | Currency management and change calculation |
| `Transaction` | Purchase transaction tracking and history |
| `ItemSlot` / `ProductSlot` | Container for inventory items |

## Architecture

The project follows an object-oriented design with:
- **Abstraction**: Base `VendingMachine` class with specialized implementations
- **Encapsulation**: Private attributes with controlled access through methods
- **MVC Pattern**: Model classes separate from GUI view
- **Composition**: Machines composed of slots, denominations, and transaction objects

## Development

This project was developed as part of CCPROG3 (Object-Oriented Programming) course requirements. It demonstrates core OOP principles including inheritance, polymorphism, encapsulation, and abstraction.

## Notes

- Minimum of 8 item slots required when creating a machine
- Special machines support ingredient-based product recipes
- All transactions are tracked in the transaction history
- Money denominations are initialized with default quantities on machine creation
