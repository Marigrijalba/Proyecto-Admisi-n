{ pkgs }: {
	deps = [
   pkgs.zulu11
		pkgs.clang
		pkgs.ccls
		pkgs.gdb
		pkgs.gnumake
	];
}