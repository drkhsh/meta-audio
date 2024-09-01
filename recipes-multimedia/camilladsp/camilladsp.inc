FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS += " alsa-lib libpulse pipewire openssl"
RDEPENDS:camilladsp += " alsa-lib libpulse pipewire pipewire-jack"

SRC_URI += " file://camilladsp.service"

inherit pkgconfig systemd

SYSTEMD_SERVICE:${PN} = "camilladsp.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

CARGO_BUILD_FLAGS += "  --no-default-features --features pulse-backend --features cpal-backend --features jack-backend --features bluez-backend --features websocket --features avoid-rustc-issue-116359"
CARGO_BUILD_FLAGS:remove = "--frozen"

RUSTFLAGS:append:raspberrypi = " -C target-feature=+neon"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/camilladsp.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} += " ${systemd_unitdir}/system/camilladsp.service"


