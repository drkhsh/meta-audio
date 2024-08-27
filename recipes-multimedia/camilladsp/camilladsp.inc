DEPENDS += " alsa-lib libpulse pipewire openssl"
RDEPENDS:camilladsp += " alsa-lib libpulse pipewire pipewire-jack"

inherit pkgconfig

CARGO_BUILD_FLAGS += "  --no-default-features --features pulse-backend --features cpal-backend --features jack-backend --features bluez-backend --features websocket --features avoid-rustc-issue-116359"
CARGO_BUILD_FLAGS:remove = "--frozen"

RUSTFLAGS:append:raspberrypi = " -C target-feature=+neon"
