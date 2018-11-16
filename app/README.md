# Mercury Android SDK

### What is Mercury?

Mercury is a distributed network built on a revolutionary person-to-person protocol and identity model aiming maximal privacy.

Our goal is to make the Internet ours again by true decentralized secure communication, social networking and enabling peer-to-peer business and apps with no middlemen, even on your phone.

Mercury is somewhat similar to a cellular mobile network, it provides features like SMS, calls, data connections, push notifications, etc, but

- built as an overlay network on top of any  transport layer (currently Tcp but could use Tor, I2P, mesh, etc) your own “cell tower” can join or leave  the network any time
- selecting a “provider” is the only trustful part  of the system
- uses cryptographic keys instead of  phone numbers for p2p encrypted communication, cell/provider and applications use the same kind  of identity
- user data and calls are encrypted, you cannot  be spied on or be cheated with contact identity
- you’re free to keep your identity and contacts  moving to another provider or application
- supports you having different unrelated identities  (family, professional, hobby, etc)  within the system
- you can restore your identities from a “cold wallet” after lost/broken device
- the network is extremely resilient, dies only with the last cell
- built to support any decentralized/distributed application on top

### What is this repository about?

This repository contains the source code of the SDK for Android dApp development. You can use the SDK to interact with the Mercury network.

Note: you need to have the Mercury Connect APP installed on your phone.

### Usage

Add this to your dApp's `build.gradle` file:

`api 'global.iop.mercury:sdk:0.1-beta'`