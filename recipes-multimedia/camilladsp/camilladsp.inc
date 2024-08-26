DEPENDS += " alsa-lib libpulse pipewire openssl"
RDEPENDS:camilladsp += " alsa-lib libpulse pipewire"

inherit pkgconfig

CARGO_BUILD_FLAGS += "  --no-default-features --features pulse-backend"
CARGO_BUILD_FLAGS:remove = "--frozen"
