DEPENDS += " alsa-lib libpulse pipewire clang-native"
RDEPENDS:librespot += " alsa-lib libpulse pipewire pipewire-jack"
export LIBCLANG_PATH = "${WORKDIR}/recipe-sysroot-native${libdir}"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://0001-aws-lc-rs-enable-bindgen-for-cross-compilation.patch file://0002-update-Cargo.lock.patch"

inherit pkgconfig systemd

CARGO_BUILD_FLAGS += " --features alsa-backend,pulseaudio-backend,jackaudio-backend"

SYSTEMD_SERVICE:${PN} = "librespot.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${S}/contrib/librespot.service ${D}/${systemd_unitdir}/system
}

FILES:${PN} += " ${systemd_unitdir}/system/librespot.service"

